USE [SaleCo]
GO
/****** Object:  Table [dbo].[CUSTOMER]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CUSTOMER](
	[CUST_NUMBER] [int] NOT NULL,
	[CUST_LNAME] [nvarchar](15) NULL,
	[CUST_FNAME] [nvarchar](15) NULL,
	[CUST_INITIAL] [nvarchar](1) NULL,
	[CUST_AREACODE] [nvarchar](3) NULL,
	[CUST_PHONE] [nvarchar](8) NULL,
	[CUST_BALANCE] [real] NULL,
 CONSTRAINT [PK_CUSTOMER] PRIMARY KEY CLUSTERED 
(
	[CUST_NUMBER] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[INVOICE]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[INVOICE](
	[INV_NUMBER] [int] NOT NULL,
	[CUST_NUMBER] [int] NULL,
	[INV_DATE] [datetime] NULL,
	[INV_SUBTOTAL] [real] NULL,
	[INV_TAX] [real] NULL,
	[INV_TOTAL] [real] NULL,
	[INV_PAY_TYPE] [nvarchar](5) NULL,
	[INV_PAY_AMOUNT] [real] NULL,
	[INV_BALANCE] [real] NULL,
 CONSTRAINT [PK_INVOICE] PRIMARY KEY CLUSTERED 
(
	[INV_NUMBER] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[qryCustomer-Invoice-Transactions-Grouped-by-Customer]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[qryCustomer-Invoice-Transactions-Grouped-by-Customer]
AS
SELECT        dbo.CUSTOMER.CUST_NUMBER, dbo.CUSTOMER.CUST_LNAME, dbo.INVOICE.INV_NUMBER, dbo.INVOICE.INV_DATE, dbo.INVOICE.INV_TOTAL, dbo.INVOICE.INV_PAY_AMOUNT, dbo.INVOICE.INV_PAY_TYPE, 
                         dbo.INVOICE.INV_BALANCE
FROM            dbo.CUSTOMER INNER JOIN
                         dbo.INVOICE ON dbo.CUSTOMER.CUST_NUMBER = dbo.INVOICE.CUST_NUMBER
GO
/****** Object:  Table [dbo].[LINE]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LINE](
	[INV_NUMBER] [int] NOT NULL,
	[LINE_NUMBER] [smallint] NOT NULL,
	[PROD_CODE] [nvarchar](10) NULL,
	[LINE_UNITS] [real] NULL,
	[LINE_PRICE] [real] NULL,
	[LINE_AMOUNT] [real] NULL,
 CONSTRAINT [PK_LINE] PRIMARY KEY CLUSTERED 
(
	[INV_NUMBER] ASC,
	[LINE_NUMBER] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PRODUCT]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PRODUCT](
	[PROD_CODE] [nvarchar](10) NOT NULL,
	[PROD_DESCRIPT] [nvarchar](35) NULL,
	[PROD_INDATE] [datetime] NULL,
	[PROD_QOH] [smallint] NULL,
	[PROD_MIN] [smallint] NULL,
	[PROD_PRICE] [real] NULL,
	[PROD_DISCOUNT] [float] NULL,
	[VEND_NUMBER] [int] NULL,
 CONSTRAINT [PK_PRODUCT] PRIMARY KEY CLUSTERED 
(
	[PROD_CODE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[qryList-all-Purchases-and-Group-by-Customer]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[qryList-all-Purchases-and-Group-by-Customer]
AS
SELECT        dbo.CUSTOMER.CUST_NUMBER, dbo.CUSTOMER.CUST_LNAME, dbo.INVOICE.INV_NUMBER, dbo.PRODUCT.PROD_CODE, dbo.LINE.LINE_UNITS, dbo.LINE.LINE_NUMBER, dbo.PRODUCT.PROD_DESCRIPT
FROM            dbo.CUSTOMER INNER JOIN
                         dbo.INVOICE ON dbo.CUSTOMER.CUST_NUMBER = dbo.INVOICE.CUST_NUMBER INNER JOIN
                         dbo.LINE ON dbo.INVOICE.INV_NUMBER = dbo.LINE.INV_NUMBER INNER JOIN
                         dbo.PRODUCT ON dbo.LINE.PROD_CODE = dbo.PRODUCT.PROD_CODE
GO
/****** Object:  Table [dbo].[ACCT_TRANSACTION]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ACCT_TRANSACTION](
	[ACCT_TRANS_NUM] [int] NOT NULL,
	[ACCT_TRANS_DATE] [datetime] NULL,
	[CUST_NUMBER] [int] NULL,
	[ACCT_TRANS_TYPE] [nvarchar](8) NULL,
	[ACCT_TRANS_AMOUNT] [real] NULL,
 CONSTRAINT [PK_ACCT_TRANSACTION] PRIMARY KEY CLUSTERED 
(
	[ACCT_TRANS_NUM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VENDOR]    Script Date: 2021-02-18 3:14:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VENDOR](
	[VEND_NUMBER] [int] NOT NULL,
	[VEND_NAME] [nvarchar](15) NULL,
	[VEND_CONTACT] [nvarchar](50) NULL,
	[VEND_AREACODE] [nvarchar](3) NULL,
	[VEND_PHONE] [nvarchar](8) NULL,
	[VEND_STATE] [nvarchar](2) NULL,
	[VEND_ORDER] [nvarchar](1) NULL,
 CONSTRAINT [PK_VENDOR] PRIMARY KEY CLUSTERED 
(
	[VEND_NUMBER] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ACCT_TRANSACTION]  WITH CHECK ADD  CONSTRAINT [FK_ACCT_TRANSACTION_CUSTOMER] FOREIGN KEY([CUST_NUMBER])
REFERENCES [dbo].[CUSTOMER] ([CUST_NUMBER])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ACCT_TRANSACTION] CHECK CONSTRAINT [FK_ACCT_TRANSACTION_CUSTOMER]
GO
ALTER TABLE [dbo].[INVOICE]  WITH CHECK ADD  CONSTRAINT [FK_INVOICE_CUSTOMER] FOREIGN KEY([CUST_NUMBER])
REFERENCES [dbo].[CUSTOMER] ([CUST_NUMBER])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[INVOICE] CHECK CONSTRAINT [FK_INVOICE_CUSTOMER]
GO
ALTER TABLE [dbo].[LINE]  WITH CHECK ADD  CONSTRAINT [FK_LINE_INVOICE] FOREIGN KEY([INV_NUMBER])
REFERENCES [dbo].[INVOICE] ([INV_NUMBER])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LINE] CHECK CONSTRAINT [FK_LINE_INVOICE]
GO
ALTER TABLE [dbo].[LINE]  WITH CHECK ADD  CONSTRAINT [FK_LINE_PRODUCT] FOREIGN KEY([PROD_CODE])
REFERENCES [dbo].[PRODUCT] ([PROD_CODE])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LINE] CHECK CONSTRAINT [FK_LINE_PRODUCT]
GO
ALTER TABLE [dbo].[PRODUCT]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_VENDOR] FOREIGN KEY([VEND_NUMBER])
REFERENCES [dbo].[VENDOR] ([VEND_NUMBER])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PRODUCT] CHECK CONSTRAINT [FK_PRODUCT_VENDOR]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[25] 4[30] 2[9] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "CUSTOMER"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 220
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "INVOICE"
            Begin Extent = 
               Top = 6
               Left = 258
               Bottom = 264
               Right = 601
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'qryCustomer-Invoice-Transactions-Grouped-by-Customer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'qryCustomer-Invoice-Transactions-Grouped-by-Customer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[13] 4[8] 2[11] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "CUSTOMER"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 136
               Right = 220
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "INVOICE"
            Begin Extent = 
               Top = 6
               Left = 258
               Bottom = 136
               Right = 449
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "LINE"
            Begin Extent = 
               Top = 6
               Left = 487
               Bottom = 136
               Right = 657
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "PRODUCT"
            Begin Extent = 
               Top = 6
               Left = 695
               Bottom = 136
               Right = 877
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 2685
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'qryList-all-Purchases-and-Group-by-Customer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane2', @value=N'End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'qryList-all-Purchases-and-Group-by-Customer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=2 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'qryList-all-Purchases-and-Group-by-Customer'
GO
