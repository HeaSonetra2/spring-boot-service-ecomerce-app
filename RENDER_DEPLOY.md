# 🚀 Render Deployment Guide (Spring Boot)

## ✅ Current Project Structure

គម្រោងរបស់អ្នកមាន `pom.xml` នៅ **root** រួចហើយ:

```
demo/
 ├─ pom.xml          ✅ (នៅ root - Render នឹង detect Java)
 ├─ src/
 │   └─ main/
 │       └─ java/
 └─ mvnw.cmd
```

👉 **Render គួរតែ detect Java automatically!**

---

## 🔧 Render Setup Steps

### 1. Create New Web Service on Render

1. Go to [Render Dashboard](https://dashboard.render.com)
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub repository

### 2. Configure Settings

**Basic Settings:**
- **Name**: `demo-api` (or your preferred name)
- **Environment**: `Java`
- **Region**: Choose closest to your users
- **Branch**: `main` (or your default branch)

**Build & Deploy:**
- **Root Directory**: (Leave **empty** - because `pom.xml` is at root)
- **Build Command**: `./mvnw clean package -DskipTests`
- **Start Command**: `java -jar target/demo-0.0.1-SNAPSHOT.jar`

**Environment Variables:**
Add these in Render dashboard:

```env
SPRING_PROFILES_ACTIVE=production
DATABASE_URL=postgresql://... (Render will provide this)
JWT_SECRET=your-secret-key-here (generate a strong secret)
```

---

## ⚠️ If Render Still Shows "Node" Instead of "Java"

### Solution 1: Check Root Directory

If your repo structure is:
```
my-repo/
 ├─ frontend/
 │   └─ package.json
 └─ backend/          ← Spring Boot here
     ├─ pom.xml
     └─ src/
```

Then in Render:
- **Root Directory**: `backend`

### Solution 2: Force Java Detection

Create `render.yaml` at repo root:

```yaml
services:
  - type: web
    name: demo-api
    env: java
    buildCommand: ./mvnw clean package -DskipTests
    startCommand: java -jar target/demo-0.0.1-SNAPSHOT.jar
    envVars:
      - key: SPRING_PROFILES_ACTIVE
        value: production
```

---

## 📝 Required Files for Render

### 1. `render.yaml` (Optional but Recommended)

Create at **repo root**:

```yaml
services:
  - type: web
    name: demo-api
    runtime: java
    buildCommand: ./mvnw clean package -DskipTests
    startCommand: java -jar target/demo-0.0.1-SNAPSHOT.jar
    envVars:
      - key: SPRING_PROFILES_ACTIVE
        value: production
      - key: DATABASE_URL
        sync: false  # Render will provide this
```

### 2. Update `application.properties`

Make sure you have production profile:

```properties
# application.properties
spring.datasource.url=${DATABASE_URL}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

Or create `application-production.properties`:

```properties
# application-production.properties
spring.datasource.url=${DATABASE_URL}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
server.port=${PORT:8080}
```

---

## 🔍 Troubleshooting

### Problem: Render detects "Node" instead of "Java"

**Check:**
1. ✅ Is `pom.xml` at repo root? → Yes (your case)
2. ✅ Is there a `package.json` at root? → If yes, delete it or move it
3. ✅ Root Directory in Render settings → Leave empty (or set to `.`)

**Fix:**
- In Render dashboard → Settings → **Root Directory**: Leave **empty** or set to `.`
- Or manually select **Environment**: `Java` in settings

### Problem: Build fails

**Common issues:**
- Maven wrapper not executable → Add `chmod +x mvnw` in build command
- Java version mismatch → Check `pom.xml` has `<java.version>17</java.version>`
- Database connection → Make sure `DATABASE_URL` is set correctly

### Problem: App crashes on start

**Check logs:**
- Database connection string format
- Port binding (Render uses `$PORT` env var)
- JWT secret is set

---

## ✅ Quick Checklist

Before deploying:

- [ ] `pom.xml` exists at root
- [ ] `mvnw` and `mvnw.cmd` are in repo
- [ ] `application.properties` configured for production
- [ ] Database URL environment variable set in Render
- [ ] JWT secret environment variable set
- [ ] Build command: `./mvnw clean package -DskipTests`
- [ ] Start command: `java -jar target/demo-0.0.1-SNAPSHOT.jar`

---

## 📚 Additional Resources

- [Render Java Documentation](https://render.com/docs/java)
- [Spring Boot on Render](https://render.com/docs/deploy-spring-boot)

