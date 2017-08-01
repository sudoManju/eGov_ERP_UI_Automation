Feature: Create Asset Depreciation in Asset Management

Scenario: Create Asset Depreciation for eligible Assets in batch process
  Prerequisites: 1.Application should be Up and Running
                 2.Assets with Capitalized status
                 3.Revaluated Assets

  StepNumber: TC_01
  Description: Asset Administrator will do a Asset Depreciation batch process
  Steps: Login as Asset Administrator, Navigate to the Create Asset Depreciation by clicking.
  Expected Result: Create Asset Depreciation page should be open
  Actual Result:
  Result: (Pass/Fail)
  Remarks:

  StepNumber: TC_02
  Description: Asset Administrator will do a Asset Depreciation batch process for the financial year
  Steps: Enter From Date and To Date
  Expected Result: From Date should be the last batch run date, and To Date should be the up to when the Asset to be depreciated.
  Actual Result:
  Result: (Pass/Fail)
  Remarks:

  StepNumber: TC_03
  Description: Validation for the Asset Search for Asset Depreciation batch.
  Steps: Enter From Date and To Date, and click Depreciation button
  Expected Result: For the entered from date and to date,
                   assets which are in the batch process should be depreciate with each of the assets respective depreciation rate.
  Result: (Pass/Fail)
  Remarks:

  Scenario: Create Asset Depreciation for non eligible Assets in batch process
  Prerequisites: Application should be Up and Running
                 Repeat the Steps TC_01 and TC_02

  StepNumber: TC_04
  Description: Validation for Assets in Depreciation Batch process.
  Steps: Enter From Date and To Date, and click Depreciation button
  Expected Result: System should not include Assets in batch which are:
                    1.Not Capitalized
                    2.Not Revaluated
                    3.Depreciation rate with zero
                    4.Land Assets
                    5.Assets whose Value is <5000
  Result: (Pass/Fail)
  Remarks:

  Scenario: Create Asset Depreciation for "Land" Assets in batch process
  Prerequisites: Application should be Up and Running
  Repeat the Steps TC_01 and TC_02

  StepNumber: TC_05
  Description: Validation for the Land Asset for Depreciation.
  Steps: Enter From Date and To Date, and click Depreciation button
  Expected Result: Asset which has the category Land should not be depreciated
  Result: (Pass/Fail)
  Remarks: