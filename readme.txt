copy the application.properties file along with the jar

specify mongodb ip in application.properties file

run the jar with -: java -jar build/libs/EcommerceWebApp.jar application.properties

AT startup of the application there are a few dummy values inserted itnnto mongodb

The request can be made as -: curl -X POST \
  'http://localhost:8060/createOrder?userId=1&productIds=jj16,jj17&productCounts=5,2'

productids can be a list of products
productCounts can be counts of ordered products in the same respective order

