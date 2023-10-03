$PROJECT_DIR = (Resolve-Path -Path "..\").Path
$ROOT_PACKAGE_DIR = (Resolve-Path -Path $($PROJECT_DIR + "\src\main\java")).Path

protoc --java_out=$ROOT_PACKAGE_DIR .\novedadesRequestBody.proto
