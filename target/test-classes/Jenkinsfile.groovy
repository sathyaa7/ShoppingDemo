triggers
        {
            github-push()
        }
pipeline
        {
            agent
                    {
                        docker
                                {
                                    image 'jenkins/jenkins:lts'
                                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                                }
                    }
            stages{
                stage('checkout')
                        {
                            steps {
                                git url: 'git@github.com:sathyaa7/ShoppingDemo.git', branch: 'master'
                            }
                        }

                            stage('Run Tests') {
                                steps {
                                    script {
                                        sh 'docker pull selenium/standalone-chrome'
                                        sh 'docker run -d -p 4444:4444 --name  chrome-container selenium/standalone-chrome'
                                        sh 'docker exec chrome-container bash -c "mvn test"'
                                        //sh 'docker stop chrome-container && docker rm chrome-container'
                                    }
                                }
                        }

            }
            post {
                always {
                    // Ensure the Chrome container is stopped and removed after the pipeline completes
                    sh 'docker stop chrome-container || true'
                    sh 'docker rm chrome-container || true'
                }
            }

        }