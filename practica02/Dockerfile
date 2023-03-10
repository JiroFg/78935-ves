FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR app
EXPOSE 5000

COPY ./78935-ves/practica02/ .
RUN dotnet restore
RUN dotnet publish -c Release -o out

FROM mcr.microsoft.com/dotnet/sdk:7.0
WORKDIR /app
COPY --from=build /app/out .
ENTRYPOINT ["dotnet","soap-test.dll"]
