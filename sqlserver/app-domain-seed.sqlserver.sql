use master;
if exists (select name from master.dbo.sysdatabases where name = N'app-domain-seed')
begin 
	drop database [app-domain-seed];
end

create database [app-domain-seed];
use [app-domain-seed];

create user "app-domain-seed" from login "app-domain-seed";

alter role [db_datareader] add member [app-domain-seed];
alter role [db_datawriter] add member [app-domain-seed];
alter role [db_ddladmin] add member [app-domain-seed];
alter role [db_owner] add member [app-domain-seed];
