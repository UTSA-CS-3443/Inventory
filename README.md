# Inventory
Repo for team: Try{} Catch() Me If You Can

## General Info
An app to view and edit an inventory

## How to run
Pull project source code and import the project into eclipse. Right click the project folder and select "Run As" and select "1 Java Application." To log into the app, use username "admin" and password "admin."

## Functionalities
All functionalities work without breaking.

The user will need proper credentials to get access to the inventory. Once granted access, the user can view, add, update, and delete items in the inventory. The user can also sort the inventory by price and other features if they want to.

When adding a new item, every field must be filled in order to add an item. The item ID can be either all numbers, strings, or a combination of the two. If any field is left empty no item will be added. In addition, all items must have a unique item ID. Once the user is done adding items, they should press the back button to go back to the inventory.

When updating an item, the Item ID field is the only one that cannot be empty. Attempting to update an item without an Item ID will do nothing. If any of the other fields are left empty, the updated item will contain the pre-existing value for that field. Once the user is done updating items, they should press the back button to go back to the inventory.

When deleting an item, the user must enter the item ID of the item they'd like to delete. Entering an empty or non-existing item ID will do nothing. While deleting items, the user can see the entire inventory while will update itself if any items are deleted. Once the user is done deleting items, they should press the back button to go back to the inventory.

Once the user is done, they can log out from the inventory page.

## Known Issues

Having a comma in an entry for an item will store the entry in multiple cells and the item won't be properly displayed in the table.

Visual indicator for an invalid item ID does not show

An extra empty column on the right when viewing an inventory with short item info

When adding the first item to an empty inventory csv, it will add to the second row. This does not break any parts of the code and so will likely not be pursued further.

## Future Additions
Visual indicators for if an item has been deleted/created/updated without having to look through the inventory

Color
