-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.4.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------


CREATE TABLE  `busroute` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Start` varchar(255) NOT NULL,
  `End` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL,
  `Priority` int(11) DEFAULT 0,
  `StationNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_uca1400_vietnamese_ai_ci;


INSERT INTO `busroute` (`Id`, `Name`, `Start`, `End`, `Price`, `Priority`, `StationNo`) VALUES
	(1, 'Tuyến 53', 'ĐH Quốc Gia TPHCM', 'Bến xe Lê Hồng Phong', 7000, 1, 32),
	(2, 'Tuyến 01', 'Bến Thành', 'Bến xe Chợ Lớn', 6000, 0, 18),
	(3, 'Tuyến 150', 'Bến xe Chợ Lớn', 'Ngã 3 Tân Vạn', 7000, 0, 41),
	(4, 'Tuyến 19', 'Bến Thành', 'ĐH Quốc Gia TPHCM', 7000, 1, 28),
	(5, 'Tuyến 02', 'Bến Thành', 'Bến xe Miền Tây', 6000, 0, 25);
