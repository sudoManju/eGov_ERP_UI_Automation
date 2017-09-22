/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2017>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.bpa.master.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.egov.bpa.autonumber.StakeHolderCodeGenerator;
import org.egov.bpa.master.entity.StakeHolder;
import org.egov.bpa.master.repository.StakeHolderAddressRepository;
import org.egov.bpa.master.repository.StakeHolderRepository;
import org.egov.bpa.transaction.entity.StakeHolderDocument;
import org.egov.bpa.transaction.entity.enums.StakeHolderType;
import org.egov.bpa.utils.BpaConstants;
import org.egov.infra.admin.master.service.RoleService;
import org.egov.infra.config.core.EnvironmentSettings;
import org.egov.infra.exception.ApplicationRuntimeException;
import org.egov.infra.filestore.entity.FileStoreMapper;
import org.egov.infra.filestore.service.FileStoreService;
import org.egov.infra.persistence.entity.Address;
import org.egov.infra.persistence.entity.CorrespondenceAddress;
import org.egov.infra.persistence.entity.PermanentAddress;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class StakeHolderService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private StakeHolderRepository stakeHolderRepository;
    @Autowired
    private StakeHolderAddressRepository stakeHolderAddressRepository;
    @Autowired
    private FileStoreService fileStoreService;
    @Autowired
    private CheckListDetailService checkListDetailService;
    @Autowired
    private StakeHolderCodeGenerator stakeHolderCodeGenerator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EnvironmentSettings environmentSettings;
    @Autowired
    private RoleService roleService;


    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    public List<StakeHolder> findAll() {
        return stakeHolderRepository.findAll();
    }

    @Transactional
    public StakeHolder save(final StakeHolder stakeHolder) {
        if (null == stakeHolder.getCode())
            stakeHolder.setCode(stakeHolderCodeGenerator.generateStakeHolderCode(stakeHolder));
        final List<Address> addressList = new ArrayList<>();
        addressList.add(setCorrespondenceAddress(stakeHolder));
        addressList.add(setPermanentAddress(stakeHolder));
        stakeHolder.setAddress(addressList);
        stakeHolder.setUsername(stakeHolder.getEmailId());
        stakeHolder.updateNextPwdExpiryDate(environmentSettings.userPasswordExpiryInDays());
        stakeHolder.setPassword(passwordEncoder.encode(stakeHolder.getMobileNumber()));
        stakeHolder.addRole(roleService.getRoleByName(BpaConstants.ROLE_BUSINESS_USER));
        stakeHolder.setActive(stakeHolder.getIsActive());
    	processAndStoreApplicationDocuments(stakeHolder);
        return stakeHolderRepository.save(stakeHolder);
    }

    protected void processAndStoreApplicationDocuments(final StakeHolder stakeHolder) {
        if (!stakeHolder.getStakeHolderDocument().isEmpty())
            for (final StakeHolderDocument applicationDocument : stakeHolder.getStakeHolderDocument()) {
                applicationDocument.setCheckListDetail(
                        checkListDetailService.load(applicationDocument.getCheckListDetail().getId()));
                applicationDocument.setStakeHolder(stakeHolder);
                if (applicationDocument.getFiles() != null) {
                    for (MultipartFile tempFile : applicationDocument.getFiles()) {
                        if (!tempFile.isEmpty()) {
                            applicationDocument.setSupportDocs(addToFileStore(applicationDocument.getFiles()));
                            applicationDocument.setIsAttached(true);
                        } else {
                            applicationDocument.setIsAttached(false);
                        }
                    }
                }
            }
    }

    protected Set<FileStoreMapper> addToFileStore(final MultipartFile[] files) {
        if (ArrayUtils.isNotEmpty(files))
            return Arrays.asList(files).stream().filter(file -> !file.isEmpty()).map(file -> {
                try {
                    return fileStoreService.store(file.getInputStream(), file.getOriginalFilename(),
                            file.getContentType(), BpaConstants.FILESTORE_MODULECODE);
                } catch (final Exception e) {
                    throw new ApplicationRuntimeException("Error occurred while getting inputstream", e);
                }
            }).collect(Collectors.toSet());
        else
            return Collections.emptySet();
    }
    @Transactional
    public StakeHolder update(final StakeHolder stakeHolder) {
        // TODO : Need to fix update address not working
        /*removeAddress(stakeHolder.getAddress());
        final List<Address> addressList = new ArrayList<>();
        addressList.add(stakeHolder.getCorrespondenceAddress());
        addressList.add(stakeHolder.getPermanentAddress());
        stakeHolder.setAddress(addressList);*/
    	processAndStoreApplicationDocuments(stakeHolder);
        return stakeHolderRepository.save(stakeHolder);
    }

    @Transactional
    public void removeAddress(final List<Address> address) {
        stakeHolderAddressRepository.deleteInBatch(address);
    }

    public StakeHolder findById(final Long id) {
        return stakeHolderRepository.findOne(id);
    }
    
    public CorrespondenceAddress setCorrespondenceAddress(final StakeHolder stakeHolder) {
        final CorrespondenceAddress correspondenceAddress = new CorrespondenceAddress();
        correspondenceAddress.setHouseNoBldgApt(stakeHolder.getCorrespondenceAddress().getHouseNoBldgApt());
        correspondenceAddress.setStreetRoadLine(stakeHolder.getCorrespondenceAddress().getStreetRoadLine());
        correspondenceAddress.setAreaLocalitySector(stakeHolder.getCorrespondenceAddress().getAreaLocalitySector());
        correspondenceAddress.setCityTownVillage(stakeHolder.getCorrespondenceAddress().getCityTownVillage());
        correspondenceAddress.setDistrict(stakeHolder.getCorrespondenceAddress().getDistrict());
        correspondenceAddress.setState(stakeHolder.getCorrespondenceAddress().getState());
        correspondenceAddress.setPostOffice(stakeHolder.getCorrespondenceAddress().getPostOffice());
        correspondenceAddress.setPinCode(stakeHolder.getCorrespondenceAddress().getPinCode());
        correspondenceAddress.setUser(stakeHolder);
        return correspondenceAddress;
    }

    public PermanentAddress setPermanentAddress(final StakeHolder stakeHolder) {
        final PermanentAddress permanentAddress = new PermanentAddress();
        permanentAddress.setHouseNoBldgApt(stakeHolder.getPermanentAddress().getHouseNoBldgApt());
        permanentAddress.setStreetRoadLine(stakeHolder.getPermanentAddress().getStreetRoadLine());
        permanentAddress.setAreaLocalitySector(stakeHolder.getPermanentAddress().getAreaLocalitySector());
        permanentAddress.setCityTownVillage(stakeHolder.getPermanentAddress().getCityTownVillage());
        permanentAddress.setDistrict(stakeHolder.getPermanentAddress().getDistrict());
        permanentAddress.setState(stakeHolder.getPermanentAddress().getState());
        permanentAddress.setPostOffice(stakeHolder.getCorrespondenceAddress().getPostOffice());
        permanentAddress.setPinCode(stakeHolder.getPermanentAddress().getPinCode());
        permanentAddress.setUser(stakeHolder);
        return permanentAddress;
    }

    @SuppressWarnings("unchecked")
    public List<StakeHolder> search(final StakeHolder stakeHolder) {
        final Criteria criteria = buildSearchCriteria(stakeHolder);
        return criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<StakeHolder> getStakeHolderListByType(final StakeHolderType stakeholderType, final String name) {
        final Criteria criteria = getCurrentSession().createCriteria(StakeHolder.class, "stakeHolder");
        criteria.add(Restrictions.eq("stakeHolder.stakeHolderType", stakeholderType));
        criteria.add(Restrictions.ilike("stakeHolder.name", name, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("stakeHolder.isActive", true));
        return criteria.list();
    }

    public Criteria buildSearchCriteria(final StakeHolder stakeHolder) {
        final Criteria criteria = getCurrentSession().createCriteria(StakeHolder.class, "stakeHolder");

        if (stakeHolder.getName() != null) {
            criteria.add(Restrictions.ilike("stakeHolder.name", stakeHolder.getName(),
                    MatchMode.ANYWHERE));
        }
        if (stakeHolder.getStakeHolderType() != null) {
            criteria.add(Restrictions.eq("stakeHolder.stakeHolderType", stakeHolder.getStakeHolderType()));
        }
        if (stakeHolder.getAadhaarNumber() != null) {
            criteria.add(Restrictions.ilike("stakeHolder.aadhaarNumber", stakeHolder.getAadhaarNumber(),
                    MatchMode.ANYWHERE));
        }
        if (stakeHolder.getPan() != null) {
            criteria.add(Restrictions.ilike("stakeHolder.pan", stakeHolder.getPan(),
                    MatchMode.ANYWHERE));
        }
        if (stakeHolder.getLicenceNumber() != null) {
            criteria.add(Restrictions.ilike("stakeHolder.licenceNumber", stakeHolder.getLicenceNumber(),
                    MatchMode.ANYWHERE));
        }
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }
    
    public boolean checkIsEmailAlreadyExists(final StakeHolder stakeHolder) {
        return stakeHolderRepository.findByEmailId(stakeHolder.getEmailId()) != null ? true : false;
    }
    
    public boolean checkIsStakeholderCodeAlreadyExists(final StakeHolder stakeHolder) {
        return stakeHolderRepository.findByCode(stakeHolder.getCode()) != null ? true : false;
    }
}
