USE [COVID_Backend]
GO

/****** Object:  Table [dbo].[Vitals]    Script Date: 2021-03-15 4:21:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vitals](
	[PatientId] [int] NOT NULL,
	[VitalsTypeId] [int] NOT NULL,
	[VitalsDT] [date] NOT NULL,
	[VitalsValue] [float] NOT NULL,
 CONSTRAINT [PK_Vitals] PRIMARY KEY CLUSTERED 
(
	[PatientId] ASC,
	[VitalsTypeId] ASC,
	[VitalsDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VitalsType]    Script Date: 2021-03-15 4:21:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VitalsType](
	[VitalsTypeId] [int] NOT NULL,
	[VitalsTypeName] [nchar](255) NULL,
 CONSTRAINT [PK_VitalsType] PRIMARY KEY CLUSTERED 
(
	[VitalsTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Vitals]  WITH CHECK ADD  CONSTRAINT [FK_Vitals_Patient] FOREIGN KEY([PatientId])
REFERENCES [dbo].[Patient] ([patient_id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Vitals] CHECK CONSTRAINT [FK_Vitals_Patient]
GO
ALTER TABLE [dbo].[Vitals]  WITH CHECK ADD  CONSTRAINT [FK_Vitals_VitalsType] FOREIGN KEY([VitalsTypeId])
REFERENCES [dbo].[VitalsType] ([VitalsTypeId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Vitals] CHECK CONSTRAINT [FK_Vitals_VitalsType]
GO

-- Insert patients
INSERT INTO Patient VALUES('James', 'Brown', '757234587');
INSERT INTO Patient VALUES('Marvin', 'Gaye', '75777761');

-- Insert vital types
INSERT INTO VitalsType VALUES(1,'HR');
INSERT INTO VitalsType VALUES(2,'Temp');
INSERT INTO VitalsType VALUES(3,'MAP');
INSERT INTO VitalsType VALUES(4,'SBP');
INSERT INTO VitalsType VALUES(5,'DBP');

----------------------
--Stored Procs
-------------------------
CREATE PROCEDURE spCreateVatalsTable
-- Create the table which has a specified name
AS
IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'Vitals')
   BEGIN
      PRINT 'The Vitals Table Exists in database.'
   END
ELSE
   BEGIN
      PRINT 'No Table in database'
	  BEGIN TRY  
			CREATE TABLE [dbo].[Vitals](
				[PatientId] [int] NOT NULL,
				[VitalsTypeId] [int] NOT NULL,
				[VitalsDT] [date] NOT NULL,
				[VitalsValue] [float] NOT NULL
			) ON [PRIMARY]

			ALTER TABLE [dbo].[Vitals] ADD  CONSTRAINT [DF_Vitals_VitalsDT]  DEFAULT (getdate()) FOR [VitalsDT]
			ALTER TABLE [dbo].[Vitals] ADD  CONSTRAINT [DF_Vitals_VitalsValue]  DEFAULT ((0)) FOR [VitalsValue]

			ALTER TABLE [dbo].[Vitals]  WITH CHECK ADD  CONSTRAINT [FK_Vitals_Patient] FOREIGN KEY([PatientId])
			REFERENCES [dbo].[Patient] ([patient_id])
			ON UPDATE CASCADE
			ON DELETE CASCADE
			ALTER TABLE [dbo].[Vitals] CHECK CONSTRAINT [FK_Vitals_Patient]
			ALTER TABLE [dbo].[Vitals]  WITH CHECK ADD  CONSTRAINT [FK_Vitals_VitalsType] FOREIGN KEY([VitalsTypeId])
			REFERENCES [dbo].[VitalsType] ([VitalsTypeId])
			ON UPDATE CASCADE
			ON DELETE CASCADE
	  END TRY 
	  BEGIN CATCH  
			PRINT 'In catch block. There is something wrong to create a table.';  
			THROW;  
	  END CATCH;
   END
RETURN
GO

CREATE PROCEDURE spInsertInfoIntoVitals 
-- Insert values
AS
BEGIN
	BEGIN TRY  
		INSERT INTO Vitals VALUES(13, 1, '01-01-2020', 85);
		INSERT INTO Vitals VALUES(13, 2, '01-01-2020', 36.1);
		INSERT INTO Vitals VALUES(13, 4, '01-01-2020', 134);
		INSERT INTO Vitals VALUES(13, 5, '01-01-2020', 91);

		INSERT INTO Vitals VALUES(14, 1, '01-02-2020', 67);
		INSERT INTO Vitals VALUES(14, 2, '01-02-2020', 37.5);
		INSERT INTO Vitals VALUES(14, 4, '01-02-2020', 145);
		INSERT INTO Vitals VALUES(14, 5, '01-02-2020', 87);

		INSERT INTO Vitals VALUES(13, 1, '01-03-2020', 81);
		INSERT INTO Vitals VALUES(13, 2, '01-03-2020', 38.1);
		INSERT INTO Vitals VALUES(13, 4, '01-03-2020', 154);
		INSERT INTO Vitals VALUES(13, 5, '01-03-2020', 97);

		INSERT INTO Vitals VALUES(14, 1, '01-04-2020', 72);
		INSERT INTO Vitals VALUES(14, 2, '01-04-2020', 37.5);
		INSERT INTO Vitals VALUES(14, 4, '01-04-2020', 157);
		INSERT INTO Vitals VALUES(14, 5, '01-04-2020', 88);
	END TRY 
	BEGIN CATCH  
		PRINT 'In catch block. There is something wrong to insert values.';  
	THROW;  
	END CATCH;
END
RETURN
GO

CREATE PROCEDURE spInsertVitalsMAP
AS
DECLARE @PatientId as int
DECLARE @Date as date
DECLARE @MAP as float

BEGIN TRY 
	DECLARE MAP_Cursor CURSOR FOR  
	SELECT SBP.PatientId,  SBP.VitalsDT, (SBP.VitalsValue + 2 * (DBP.VitalsValue)) / 3 AS MAP FROM 
	((SELECT PatientId, VitalsDT, VitalsValue FROM Vitals WHERE VitalsTypeId = 4) SBP
	INNER JOIN
	(SELECT PatientId, VitalsDT, VitalsValue FROM Vitals WHERE VitalsTypeId = 5) DBP
	ON SBP.PatientId = DBP.PatientId AND SBP.VitalsDT = DBP.VitalsDT) 

	OPEN MAP_Cursor;  
	FETCH NEXT FROM MAP_Cursor INTO @PatientId, @Date, @MAP;  
	WHILE @@FETCH_STATUS = 0  
		BEGIN  
	--      Print CONVERT(VARCHAR(50), @Date) + '   ' + CONVERT(VARCHAR(10), @PatientId) + '      ' +  @MAP 
			INSERT INTO Vitals VALUES(@PatientId, 3, @Date, @MAP)
			FETCH NEXT FROM MAP_Cursor INTO @PatientId, @Date, @MAP;  
		END;  
	CLOSE MAP_Cursor;  
	DEALLOCATE MAP_Cursor;  
END TRY 
BEGIN CATCH  
	PRINT 'In catch block. There is something wrong to insert values.';  
THROW;  
END CATCH;
RETURN
GO

CREATE PROCEDURE spReportVitals
AS
DECLARE @PatientName as NVARCHAR(256)
DECLARE @MAP as float
DECLARE @Date as date

BEGIN TRY 
	DECLARE Vitals_Cursor CURSOR FOR  
	SELECT p.first_name + ' ' + p.last_name AS pName, v.VitalsValue, v.VitalsDT 
	FROM Patient p 
	INNER JOIN Vitals v ON p.patient_id = v.PatientID
	WHERE v.VitalsTypeId = 3 ORDER BY v.VitalsDT ASC, pName ASC

	OPEN Vitals_Cursor;  
	FETCH NEXT FROM Vitals_Cursor INTO @PatientName, @MAP, @Date;  
	WHILE @@FETCH_STATUS = 0  
	   BEGIN  
--		  Print @Date + '   ' + @PatientName + '      ' +  @MAP 
		  Print CONVERT(NVARCHAR(50), @Date) + '   ' + @PatientName + '      ' +  CONVERT(NVARCHAR(50), @MAP)
		  FETCH NEXT FROM Vitals_Cursor INTO @PatientName, @MAP, @Date;  
	   END;  
	CLOSE Vitals_Cursor;  
	DEALLOCATE Vitals_Cursor;                                                                                                                                                                                                                                                                                                                                                                                                                                                   
END TRY 
BEGIN CATCH  
	PRINT 'In catch block. There is something wrong to print out.';  
THROW;  
END CATCH;
RETURN
GO

EXEC spCreateVatalsTable;
EXEC spInsertInfoIntoVitals;
EXEC spInsertVitalsMAP;
EXEC spReportVitals;