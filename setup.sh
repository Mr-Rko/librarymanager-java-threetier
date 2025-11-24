#!/bin/bash

echo "🚀 Starting Library Manager deployment..."
sleep 1

# Check Docker
if ! docker info > /dev/null 2>&1; then
    echo "❌ Error: Docker is not running"
    exit 1
fi

echo "✅ Docker is running"

# Stop existing containers
echo "🛑 Stopping any existing containers..."
docker compose down

echo "Building the application..."

# Check if source files exist
if [ ! -f "pom.xml" ]; then
    echo "❌ pom.xml not found. Are you in the correct directory?"
    exit 1
fi

if [ ! -d "src" ]; then
    echo "❌ src directory not found. Are you in the correct directory?"
    exit 1
fi

echo "Starting MySQL container..."
docker compose up --build -d mysql


echo "⏳ Waiting for MySQL to be ready..."

for i in {1..30}; do
    if docker exec mysql mysqladmin ping -hmysql -uroot -proot --silent > /dev/null 2>&1; then
        echo "✅ MySQL is ready!"
        break
    fi
    echo "⏳ MySQL not ready yet... ($i/30)"
    sleep 2
done

# If MySQL never becomes ready, exit with error
if ! docker exec mysql mysqladmin ping -hmysql -uroot -proot --silent > /dev/null 2>&1; then
    echo "❌ MySQL failed to start after waiting!"
    docker logs mysql
    exit 1
fi

# Execute SQL file if exists
if [ -f "database_setup.sql" ]; then
    echo "📦 Setting up database from SQL file..."
    docker exec -i mysql mysql -hmysql -uroot -proot < database_setup.sql
    echo "✅ Database created successfully!"
else
    echo "⚠️ No database_setup.sql found, skipping DB import."
fi

echo "MySQL is ready"

# Start application
echo "🚀 Starting application container..."
docker compose up --build -d 

echo "Checking application status..."

echo
echo "=== Container Status ==="
docker compose ps

echo
echo "✅ Deployment complete!"
echo "🌐 Application: http://localhost:8080"
echo "📋 Logs: docker compose logs -f"
echo "🛑 Stop: docker compose down"
