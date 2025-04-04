# Use Node.js 22 for building and running the Next.js app
FROM node:22 AS build

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Next.js app
RUN npm run build

# Use Node.js to serve the Next.js app
FROM node:22 AS production

# Set working directory
WORKDIR /app

# Copy only the built application and dependencies
COPY --from=build /app ./

# Expose port 3000
EXPOSE 3000

# Start the Next.js application
CMD ["npm", "start"]