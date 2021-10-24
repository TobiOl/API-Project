# Northwind local API
An API from the perspective of an employee. They have access to the customers, orders and products part of the database but no others.

---

### Technology/Architecture used
This was developed using Spring in a REST form, also using Data Transfer Object patterns for making sure the employee only sees relevant information for their usage.

---
### Endpoints available
Products
- Products: Shows all available products
- products?id=: shows specific products by their id
- products?lowerBoundStock=: shows all products with stock higher than the value entered
- products?lowerBoundPrice=: shows all products with price higher than the value entered
Orders
- orders: Shows all available orders
- orders?id=: shows specific orders by their id
- orders?city=: shows orders according to their shipping city
- orders?country=: shows orders according to their shipping country
- orders?region=: shows orders according to their shipping region
Customers
- customers: Shows all available customers
- customers/location?country/city/region=: shows all customers according to country, city or region. Note that you can further filter by one of the other parameters by using & in between.

---
### Further features
Customer error message handling has also been implemented for illegal arguments being entered and for when values cannot be found
