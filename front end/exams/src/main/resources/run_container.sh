#!/bin/sh
image_name='exams_mssql'
if [ $1 ]; then
  image_name=$1
fi

docker run --name $image_name -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=P@ssw0rd' -e 'MSSQL_PID=Express' -e 'MSSQL_COLLATION=Arabic_CI_AS' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
