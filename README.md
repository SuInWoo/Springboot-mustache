# π§βπ» Springboot-mustache

## ERD


![image](https://user-images.githubusercontent.com/63344592/206087642-d15b37d8-2090-4946-b1d9-738e7acea629.png)


## ποΈ deploy.sh

```
# λ³μ μ μ₯
CONTAINER_NAME=${1}  // νμ¬ μ€νλκ³  μλ μ»¨νμ΄λ μ΄λ¦
PROJECT_NAME=${2} // νλ‘μ νΈ λͺ
IMAGE_NAME=${3} // μ΄λ―Έμ§ μ΄λ¦
URL=${4} // URL ex)ec2-15-165-59-7.ap-northeast-2.compute.amazonaws.com:3306/likelion_db
PASSWORD=${5} // DB PassWord

# λ°°ν¬ μμ
echo "> λ°°ν¬λ₯Ό μμνκ² μ΅λλ€."
cd ~

# κΈ°μ‘΄ container stop
echo "> κΈ°μ‘΄ μ»¨νμ΄λ λ΄λ¦¬κΈ°"
docker stop $CONTAINER_NAME

# git clone λ°μ μμΉλ‘ μ΄λ
cd $PROJECT_NAME/

# git λ΄λ € λ°κΈ°
echo "> Git Pull"
git pull

# gradle build
echo "> Docker Build"
docker build -t $IMAGE_NAME .

# docker run
echo "> Docker Run"
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://$URL -e SPRING_DATASOURCE_PASSWORD=$PASSWORD -d $IMAGE_NAME

# λ°°ν¬ μ’λ£
echo " > λ°°ν¬κ° μ’λ£λμμ΅λλ€."
```

## π» μ€ν μμ
```
1. vim deploy.sh
2. chmod 755 deploy.sh
3. sh deploy.sh <μ’λ£ν  μ»¨νμ΄λ μ΄λ¦> <νλ‘μ νΈ μ΄λ¦> <μ΄λ―Έμ§ μ΄λ¦> <URL> <DB PassWord>
```

