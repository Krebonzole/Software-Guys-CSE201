DROP DATABASE IF EXITS EZPZFinances
GO

CREATE DATABASE EZPZFinances
GO

USE EZPZFinances
GO

CREATE TABLE Accounts (
	accountID		int		not null	primary key		identity,
	buisnessID		int		not null	foreign key references Buisness(buisnessID),
	accountDesc		varchar(100)	not null,
	userName		varchar(50)	not null,
	passWord		varchar(20)	not null
);
GO

CREATE TABLE Transactions (
	businessID		int		not null	primary key		identity,
	accountID		int		not null	foreign key references Accounts(accountID),
	Name			varchar(100)	not null,
	PhoneNumber		varchar(20)	not null,
	Address		varchar(500)	not null
);
GO

CREATE TABLE Balance (
	balanceID		int		not null	primary key		identity,
	businessID		int		not null	foreign key references Buisness(businessID)
	balPurpose		varchar(100)	not null,
	dangerZone		int		not null,
	money			int		not null
);
GO

CREATE TABLE Transactions (
	transactionID		int 		not null	primary key		identity,
	accountID		int 		not null	foreign key references Accounts(accountID),
	balanceID		int		not null	foreign key references Balance(balanceID),
	purpose 		varchar(100) 	not null,
	[net change]	int		not null
);
GO


