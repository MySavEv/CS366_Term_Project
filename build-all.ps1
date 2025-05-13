# Paths to your Maven projects
$projects = @(
    "Order_Service",
    "Payment_Service",
    "Restaurant_Service",
    "Rider_Service",
    "Notification-service"
)

$jobs = @()

# Start mvnw build for each project in a background job
foreach ($proj in $projects) {
    $jobs += Start-Job -ScriptBlock {
        param($p)
        Write-Host "Building project at $p"
        Set-Location -Path $p
        ./mvnw clean package -DskipTests
    } -ArgumentList $proj
}

# Wait for all background jobs to finish
$jobs | ForEach-Object {
    Wait-Job $_
    Receive-Job $_
    Remove-Job $_
}

Write-Host "✅ All builds completed."
