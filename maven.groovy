def build_test(){
  echo 'maven build & test...'
  sh "./mvnw clean package -e" 
}

def run_app(){
  echo "run..."
  sh "./mvnw spring-boot:run &"
}

return this