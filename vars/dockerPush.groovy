def call() {
    sh '''
            az login --service-principal --username $AZURE_CLIENT_ID --password $AZURE_SECRET --tenant $AZURE_TENANT
            az acr login --name nareshgdevops
            docker push nareshgdevops.azurecr.io/roboshop-$APP_NAME:$GIT_COMMIT
       '''
}