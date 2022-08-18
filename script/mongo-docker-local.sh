docker run \
  -d \
  --name mongo \
  -p 27017:27017 \
  -v /Users/yr.han/mongodb/data:/data/db \
  -e MONGO_INITDB_ROOT_USERNAME=kneedle \
  -e MONGO_INITDB_ROOT_PASSWORD=kneedle \
  mongo

# 컨테이너 접속해서 확인하기
# docker exec -it mongo bash
# mongo --username kneedle --password kneedle
