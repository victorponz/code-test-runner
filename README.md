# Para crear el jar
https://chatgpt.com/share/66ea86cf-c64c-8007-8f7b-7c4212f8faa6
Luego añadir Main-Class: CodeTestRunner mediante jar app

## Para ejecutar el test
docker build -t codetestrunner .
docker run  --rm -v $(pwd)/6:/6 --name codetestrunner codetestrunner My 6/
Donde My es el nombre de la clase a probar `6` es el directorio compartido con el host

