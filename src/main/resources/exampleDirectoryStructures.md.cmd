
=================================================================================
=================================================================================
Myapp.Domain - All domain specific classes share this namespace
Myapp.Data - Thin layer that abstracts the database from the domain.
Myapp.Application - All "support code", logging, shared utility code, service consumers etc
Myapp.Web - The web UI

So classes will be for example:

Myapp.Domain.Sales.Order
Myapp.Domain.Sales.Customer
Myapp.Domain.Pricelist
Myapp.Data.OrderManager
Myapp.Data.CustomerManager
Myapp.Application.Utils
Myapp.Application.CacheTools
Etc.

=================================================================================
=================================================================================
myapp
    .domain
        .persistence