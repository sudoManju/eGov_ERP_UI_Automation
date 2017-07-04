Feature: Creating Master Data for Trade License


  As a TL Admin I want to create a license category


  Scenario: Create license category

    Given commissioner logs in
    And user will select the required screen as "Create License Category"
    And he choose to enter category details
    And he creates the license category
    And user will be notified by "View"
    And he closes the view page
    And current user logs out


  Scenario: Create Unit of Measurement

    Given commissioner logs in
    And user will select the required screen as "Create Unit Of Measurement"
    And he choose to enter create UOM details
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And user will be notified by "View"
    And he closes the view page
    And current user logs out



  Scenario: Create License Sub Category

    Given commissioner logs in

    # Create License Category
    And user will select the required screen as "Create License Category"
    And he choose to enter category details
    And he creates the license category
    And he closes the view page

    #Create UOM
    And user will select the required screen as "Create Unit Of Measurement"
    And he choose to enter sub category details
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And he closes the view page

    #Create sub category
    And user will select the required screen as "Create License SubCategory"
    And he selects the above created category
    And he choose to enter sub category details
    And he enters fee type as "License Fee" Rate Type as "Flat_by_Range" and UOM
    And he creates the Sub Category
    And user will be notified by "View"
    And he close ack page
    And current user logs out

  @FeeMatrix @Master
  Scenario: Create Fee Matrix

   Given commissioner logs in
  # Create License Category
    And user will select the required screen as "Create License Category"
    And he choose to enter category details
    And he creates the license category
    And he closes the view page

    #Create UOM
    And user will select the required screen as "Create Unit Of Measurement"
    And he choose to enter create UOM details
    And he checks the Active checkbox
    And he creates the Unit Of Measurement
    And he closes the view page

    #Create sub category
    And user will select the required screen as "Create License SubCategory"
    And he selects the above created category
    And he choose to enter sub category details
    And he enters fee type as "License Fee" Rate Type as "Flat_by_Range" and UOM
    And he creates the Sub Category
    And he close ack page
    
    #Create fee matrix
    
    And user will select the required screen as "Create Fee Matrix"
    And he enters fee matrix details
    And he creates the fee matrix
    And user will be notified by "Fee"
    And he closes the View Fee Matrix page

    #Create License Document Type
    And user will select the required screen as "Create Document Type"
    And he enters document type details
    And user will be notified by "Document"

    
    
    And current user logs out
