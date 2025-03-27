
// LIST OF API CALLS


// --------------- INVOICE

// localhost:8080/invoice/createInvoice
// {
//     "invoiceNumber":"126",
//     "issueDate":"2020-12-31T15:53:16",
//     "customerId":"09e43fba-2acb-4da9-9c44-0104237fee9e",
//     "totalAmount":10
// }

// localhost:8080/invoice/getAllInvoices
// localhost:8080/invoice/getInvoiceById?id=36839d48-9919-4b3f-aeab-7eb941e6775a

// localhost:8080/invoice/updateInvoice?id=36839d48-9919-4b3f-aeab-7eb941e6775a
// {
//     "invoiceNumber":"126",
//     "issueDate":"2020-12-31T15:53:16",
//     "customerId":"09e43fba-2acb-4da9-9c44-0104237fee9e",
//     "totalAmount":33
// }

// localhost:8080/invoice/deleteInvoice?id=36839d48-9919-4b3f-aeab-7eb941e6775a

// --------------- INVOICE ITEM

// localhost:8080/invoiceitem/createInvoiceItem
// {
//      "invoiceId":"71f34ea5-6bf9-4d88-92e9-f2541f1f3974",
//      "productId":"3f929077-4522-4049-bb78-b6b512816ec3",
//      "quantity":10,
//      "amount":10
// }

// localhost:8080/invoiceitem/getAllInvoiceItems?id=71f34ea5-6bf9-4d88-92e9-f2541f1f3974
// localhost:8080/invoiceitem/getInvoiceItemById?id=1b32f153-15ab-425a-8b5a-6ca60a92cbaa

// localhost:8080/invoiceitem/updateInvoiceItem?id=05229c8e-8073-44b7-9971-c02c3f90e1f3
// {
//      "invoiceId":"71f34ea5-6bf9-4d88-92e9-f2541f1f3974",
//      "productId":"3f929077-4522-4049-bb78-b6b512816ec3",
//      "quantity":22,
//      "amount":22
// }

// localhost:8080/invoiceitem/deleteInvoiceItem?id=05229c8e-8073-44b7-9971-c02c3f90e1f3