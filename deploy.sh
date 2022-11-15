# 변수 저장
CONTAINER_NAME=${1}
PROJECT_NAME=${2}
IMAGE_NAME=${3}
URL=${4}
PASSWORD=${5}

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
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://$URL -e SPRING_DATASOURCE_PASSWORD=$PASSWORD -d $IMAGE_NAME

# 배포 종료
echo " > 배포가 종료되었습니다."