FROM openjdk:17 AS backend_build

# Set a working directory
WORKDIR /app

# Copy backend build results 
COPY ${backend_directory}/build/libs/*.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/app.jar"]
