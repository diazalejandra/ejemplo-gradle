pipeline {
    agent any
	
	tools {
		gradle 'gradle'
		maven 'maven'
	}
    stages {
        stage('build & test') {
            steps {
                echo 'Compiling...'
                sh "./gradlew build"          
            }
        }

        stage('sonar') {
           steps{
              withSonarQubeEnv(credentialsId:'newtoken',installationName:'SonarServer') { 
					script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew sonar:sonar \
                                     -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        } else {
                            echo 'Windows OS'
                                bat 'gradlew sonar:sonar \
                                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        }
					}
             }
          }
        } 		
    }    
}