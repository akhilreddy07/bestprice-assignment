Implementation Details:
  1. Created a spring-boot with embedded tomcat.
  2.  Used a hashp map which stores the Items wise discounts and even with Items type there's hash map which can be used to store specific item id related discounts.
  3. Added methods in service layer to compute the discounts/adding and removing the discounts.

Added following unit tests.

1. testInvalidDiscounts.
2. tesCreateDiscounts
3. testDiscountForClothesAndElectronicsTotalAbove100Dollars
4. testMixOfItemWithoutDiscountAndWithDiscount
5. testDiscountForClothesWithIdWhichHasDiscount
6. testDiscountForClothesAndElectronics
7. tesDeleteDiscounts
8. testDiscountForClothes
9.testGetDiscounts
