def build_test(){
  echo ' gradle build & test...'
  sh "gradle build"
}

def run_app(){
  echo 'run...'
  sh "nohup gradle bootRun > /tmp/mscovid.log 2>&1 &" 
}

def sonar(){
  echo 'Sonar scan in progress.....'
  sh "./gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
  echo '.....Sonar scan completed'
}

return this