import liquidtemplates.HapiComponent
import liquidtemplates.HapiParent
import liquidobjects.HapiService

// Folders
def workspaceFolderName = "${WORKSPACE_NAME}"
def projectFolderName = "${PROJECT_NAME}"

// array of microservice objects 
def microServiceArray = [
        new HapiService([
                bddTests: false,
                isSubFolder: true,
                dockerContainerName: "hapi-dynamo-container",
                componentName: "hapi-dynamo",
                serviceNameOverride: "hapi-dynamo",
                applicationRepoURL:"ssh://git@innersource.accenture.com/lsad/sample-hapijs-service.git",
                dockerRepositoryURI: "835985111799.dkr.ecr.us-west-2.amazonaws.com/adop-hapijs-service",
                dockerImageTag: "latest",
                separateBuildAndAppImage: true,
                awsRegion: "us-west-2",
                deploymentServerElasticIPAddress: "54.218.8.195",
                environmentFolderName: "/workspace/LiquidStudio/Studio_Session/Environment/Instance/Create_Docker_Server",
                uniqueEnvName: "LACS-DEV"
        ]),

]

// Provision all microservices jobs and pipelines
for (service in microServiceArray)
{
        printf "\n====== Deploying Component Pipelines and jobs for ${service.componentName} ======\n\n"

        // Generate Parent job
        new HapiComponent(this, workspaceFolderName, projectFolderName, service);
}

new HapiParent(this, workspaceFolderName, projectFolderName, microServiceArray);
