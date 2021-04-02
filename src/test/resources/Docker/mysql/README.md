# docker-magic

## build local img
```
docker build -t mysql_local PATH_TO_THIS_DOCKER_FILE
```

## run local img
```
docker run --name mysql_local --rm -p 33066:3306 -d mysql_local:latest
```

## to run mysql console
```
docker exec -it mysql_local mysql -uroot -padmin 
```