def build_test(){
  echo 'maven build & test...'
  sh "./mvnw clean package -e" 
}

def run_app(){
  echo "run..."
  sh "./mvnw spring-boot:run  > /tmp/mscovid.log 2>&1 &"
}

def sonar(){
  echo 'Sonar scan in progress.....'
  sh "./mvnw clean verify sonar:sonar -Dsonar.projectKey=ejemplo-maven"
  echo '.....Sonar scan completed'
}

return this