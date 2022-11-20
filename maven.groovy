def build_test(){
  echo 'maven build & test...'
  sh "./mvnw clean package -e" 
}

def run_app(){
  echo "run..."
  sh "./mvnw spring-boot:run  > /tmp/mscovid.log 2>&1 &"
  sh 'sleep 5'
  sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this