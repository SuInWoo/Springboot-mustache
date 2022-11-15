# Springboot-mustache

## deploy.sh

```
# 변수 저장
CONTAINER_NAME=${1}  // 현재 실행되고 있는 컨테이너 이름
PROJECT_NAME=${2} // 프로젝트 명
IMAGE_NAME=${3} // 이미지 이름
URL=${4} // URL ex)ec2-15-165-59-7.ap-northeast-2.compute.amazonaws.com:3306/likelion_db
PASSWORD=${5} // DB PassWord

# 배포 시작
echo "> 배포를 시작하겠습니다."
cd ~

# 기존 container stop
echo "> 기존 컨테이너 내리기"
docker stop $CONTAINER_NAME

# git clone 받은 위치로 이동
cd $PROJECT_NAME/

# git 내려 받기
echo "> Git Pull"
git pull

# gradle build
echo "> Docker Build"
docker build -t $IMAGE_NAME .

# docker run
echo "> Docker Run"
docker run -d -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://$URL -e SPRING_DATASOURCE_PASSWORD=$PASSWORD -d $IMAGE_NAME

# 배포 종료
echo " > 배포가 종료되었습니다."
```

## 실행 예시
```
1. vim deploy.sh
2. chmod 755 deploy.sh
3. sh deploy.sh <종료할 컨테이너 이름> <프로젝트 이름> <이미지 이름> <URL> <DB PassWord>
```
