sudo docker run -p 5433:5432 --name inventory -e POSTGRES_PASSWORD=mysecretpassword -d postgres

sudo docker start inventory

sudo docker rm inventory

