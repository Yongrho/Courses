SELECT CUSTOMER.CUST_NUMBER, 
	   CUSTOMER.CUST_LNAME, 
	   INVOICE.INV_NUMBER, 
	   INVOICE.INV_DATE, 
	   INVOICE.INV_TOTAL, 
	   INVOICE.INV_PAY_AMOUNT, 
	   INVOICE.INV_PAY_TYPE, 
	   INVOICE.INV_BALANCE
FROM CUSTOMER INNER JOIN INVOICE ON CUSTOMER.CUST_NUMBER = INVOICE.CUST_NUMBER;

SELECT        dbo.CUSTOMER.CUST_NUMBER, dbo.CUSTOMER.CUST_LNAME, dbo.INVOICE.INV_DATE, dbo.INVOICE.INV_SUBTOTAL, dbo.INVOICE.INV_TAX, dbo.INVOICE.INV_TOTAL, dbo.INVOICE.INV_PAY_TYPE, 
                         dbo.INVOICE.INV_NUMBER
FROM            dbo.CUSTOMER INNER JOIN
                         dbo.INVOICE ON dbo.CUSTOMER.CUST_NUMBER = dbo.INVOICE.CUST_NUMBER