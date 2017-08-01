package builders.assetManagement.assetService;

import entities.requests.assetManagement.assetServices.create.YearWiseDepreciation;

public class YearWiseDepreciationBuilder {

YearWiseDepreciation yearWiseDepreciation = new YearWiseDepreciation();

public YearWiseDepreciationBuilder(int i){

    switch (i){

        case 0:
           yearWiseDepreciation.setDepreciationRate(2);
           yearWiseDepreciation.setFinancialYear("2012");
           yearWiseDepreciation.setUsefulLifeInYears(6);
           break;

        case 1:
            yearWiseDepreciation.setDepreciationRate(2);
            yearWiseDepreciation.setFinancialYear("2013");
            yearWiseDepreciation.setUsefulLifeInYears(11);
            break;

        case 3:
            yearWiseDepreciation.setDepreciationRate(2);
            yearWiseDepreciation.setFinancialYear("2014");
            yearWiseDepreciation.setUsefulLifeInYears(15);
    }
}

 public YearWiseDepreciation build(){
    return yearWiseDepreciation;
 }
}
