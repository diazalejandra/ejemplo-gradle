def build_test(){
  echo ' gradle build & test...'
  sh "gradle build"
}

def run_app(){
  sh './gradlew bootrun &'
  sh 'sleep 5'
  sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this