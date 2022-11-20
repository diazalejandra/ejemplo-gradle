pipeline {
    agent any
	
	tools {
		gradle 'gradle'
		maven 'maven'
	}
	
    stages {
        stage('build & test') {
            steps {
                echo 'Source code compilation in progress.....'
                script {
                    if(isUnix()) {
                        echo 'Unix OS'
						sh './mvnw clean install'
						sh './gradlew build'
                    } else {
                        echo 'Windows OS'
                        bat 'mvnw clean install'
						bat 'gradlew build'
                    }
                }
                echo '.....Source code compilation completed'       
            }
        }

        stage('sonar') {
			steps{
				echo 'Sonar scan in progress.....'
				withSonarQubeEnv(credentialsId:'newtoken',installationName:'SonarServer') { 
					script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew sonarqube \
                                     -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        } else {
                            echo 'Windows OS'
                                bat 'gradlew sonarqube \
                                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        }
					}
				}
				echo '.....Sonar scan completed'
			}
        }

		stage('run & test') {
            steps {
				echo 'Gradle run in progress.....'
                script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew bootrun &'
								sh 'sleep 5'
								sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                        } else {
							echo 'Windows OS'
								bat 'gradlew bootrun &'
								sh 'sleep 5'
								sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                        }
                        echo '.....Gradle run completed'
                    }
            }
        }
    }    
}