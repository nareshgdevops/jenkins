def call() {
    def secrets = [
            [
                    path: 'roboshop-infra/azure-service-priniciple',
                    engineVersion: 2,
                    secretValues: [
                            [envVar: 'AZURE_SUBSCRIPTION_ID', vaultKey: 'AZURE_SUBSCRIPTION_ID'],
                            [envVar: 'AZURE_CLIENT_ID', vaultKey: 'AZURE_CLIENT_ID'],
                            [envVar: 'AZURE_SECRET',    vaultKey: 'AZURE_SECRET'],
                            [envVar: 'AZURE_TENANT',    vaultKey: 'AZURE_TENANT']
                    ]
            ],
            [
                    path: 'roboshop-infra/sonarqube',
                    engineVersion: 2,
                    secretValues: [
                            [envVar: 'SONAR_TOKEN', vaultKey: 'sonar_token']
                    ]
            ]
    ]

    withVault([vaultSecrets: secrets]) {
        env.AZURE_SUBSCRIPTION_ID = AZURE_SUBSCRIPTION_ID
        env.AZURE_CLIENT_ID = AZURE_CLIENT_ID
        env.AZURE_SECRET    = AZURE_SECRET
        env.AZURE_TENANT    = AZURE_TENANT
        env.SONAR_TOKEN     = SONAR_TOKEN
    }
}