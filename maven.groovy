def build_test(){
  echo 'maven build & test...'
  sh "./mvnw clean package -e" 
}

def run_app(){
  echo "run..."
  sh "./mvnw spring-boot:run &"
  sh 'sleep 5'
  sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this