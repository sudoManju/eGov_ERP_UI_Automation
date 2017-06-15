Feature: Creating Master Data for Trade License


  As a TL Admin I want to create a license category

  Scenario: Create license category

    Given commissioner logs in
    And user will select the required screen as "Create License Category"
    And he enters the license category name as "Motor Vehicles4" and code as "motorVehicleCate"
    And he creates the license category
    And user will be notified by "View"
    And he closes the view page
    And current user logs out


  Scenario: Create Unit of Measurement

    Given commissioner logs in
    And user will select the required screen as "Create Unit Of Measurement"
    And he enters the Unit Of Measurement name as "Newton" and code as "Newton"
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And user will be notified by "View"
    And he closes the view page
    And current user logs out



  Scenario: Create License Sub Category

    Given commissioner logs in

    # Create License Category
    And user will select the required screen as "Create License Category"
    And he enters the license category name as "Motor Vehicles7" and code as "motorVehicleCategor"
    And he creates the license category
    And he closes the view page

    #Create UOM
    And user will select the required screen as "Create Unit Of Measurement"
    And he enters the Unit Of Measurement name as "Omega" and code as "Omega"
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And he closes the view page

    #Create sub category
    And user will select the required screen as "Create License SubCategory"
    And he selects the above created category
    And he enters the sub category name as "Two Wheeler" and code as "TwoWheeler"
    And he enters fee type as "License Fee" Rate Type as "Flat_by_Range" and UOM
    And he creates the Sub Category
    And user will be notified by "View"
    And he close ack page
    And current user logs out

  @FeeMatrix
  Scenario: Create Fee Matrix

   Given commissioner logs in
  # Create License Category
    And user will select the required screen as "Create License Category"
    And he enters the license category name as "Motor Vehicles1" and code as "motorVehicles"
    And he creates the license category
    And he closes the view page

    #Create UOM
    And user will select the required screen as "Create Unit Of Measurement"
    And he enters the Unit Of Measurement name as "Forces" and code as "Forces"
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And he closes the view page

    #Create sub category
    And user will select the required screen as "Create License SubCategory"
    And he selects the above created category
    And he enters the sub category name as "Two Wheelers" and code as "TwoWheelers"
    And he enters fee type as "License Fee" Rate Type as "Flat_by_Range" and UOM
    And he creates the Sub Category
    And he close ack page
    
    #Create fee matrix
    
    And user will select the required screen as "Create Fee Matrix"
    And he enters fee matrix details
    
#    And current user logs out
