insert into eg_roleaction (roleid, actionid) values ((select id from eg_role where name = 'Works Approver'),(select id from eg_action where name ='WorksRevisionEstimateSuccessPage' and contextroot = 'egworks'));

--rollback delete FROM EG_ROLEACTION WHERE roleid = (SELECT id FROM eg_role WHERE name = 'Works Approver') and actionid = (SELECT id FROM eg_action WHERE name ='WorksRevisionEstimateSuccessPage' and contextroot = 'egworks');