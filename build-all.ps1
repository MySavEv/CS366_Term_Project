$projectDirs = @(
    "Order_Service",
    "Payment_Service",
    "Restaurant_Service",
    "Notification-service",
    "Rider_Service",
    "Customer_Service"
)

foreach ($dir in $projectDirs) {
    Write-Host "nBuilding project in '$dir'..." -ForegroundColor Cyan

    Push-Location $dir

    if (Test-Path "./mvnw") {

        ./mvnw clean package -DskipTests
    } elseif (Test-Path "./mvnw.cmd") {
        .\mvnw.cmd clean package -DskipTests
    } else {
        Write-Host "No mvnw or mvnw.cmd found in $dir. Skipping." -ForegroundColor Yellow
    }

    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Build failed in $dir" -ForegroundColor Red
        Pop-Location
        exit $LASTEXITCODE
    } else {
        Write-Host "✅ Build succeeded in $dir" -ForegroundColor Green
    }

    Pop-Location
}

Write-Host "nAll projects built successfully." -ForegroundColor Green