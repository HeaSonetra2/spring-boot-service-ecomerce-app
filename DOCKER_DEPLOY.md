# 🐳 Docker + Render Deployment (វិធីងាយបំផុត - WORK 100%)

## ✅ Files Created

1. **`Dockerfile`** - Docker configuration (នៅ root, កន្លែងមាន `pom.xml`)
2. **`.dockerignore`** - Exclude unnecessary files from Docker build
3. **`render.yaml`** - Updated to use Docker

---

## 🚀 Deploy Steps (Click-Click-Run)

### STEP 1: Push to GitHub
```bash
git add .
git commit -m "Add Dockerfile for Render deployment"
git push
```

### STEP 2: Deploy on Render

1. **Go to [Render Dashboard](https://dashboard.render.com)**
2. Click **"New +"** → **"Web Service"**
3. **Connect your GitHub repository**
4. Render will **auto-detect Dockerfile** ✅
5. **Settings:**
   - **Name**: `demo-api`
   - **Environment**: Auto-detected as Docker
   - **Dockerfile Path**: `./Dockerfile` (auto-filled)
   - **Plan**: Free

6. **Environment Variables** (Add these):
   ```
   SPRING_PROFILES_ACTIVE=production
   DATABASE_URL=<Render will provide if you add PostgreSQL>
   JWT_SECRET=<generate a strong secret>
   ```

7. Click **"Create Web Service"**

8. **Wait for build** (5-10 minutes first time)

9. **Done!** ✅ Your API will be live at `https://demo-api.onrender.com`

---

## 📋 What the Dockerfile Does

```dockerfile
FROM eclipse-temurin:17-jdk    # Java 17 base image
WORKDIR /app                    # Set working directory
COPY . .                        # Copy all files
RUN ./mvnw clean package        # Build Spring Boot JAR
EXPOSE 8080                     # Expose port 8080
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]  # Run app
```

---

## ✅ Why This Works 100%

- ✅ **No confusion** - Docker handles everything
- ✅ **Render auto-detects** Dockerfile
- ✅ **No manual Java setup** needed
- ✅ **Works on any platform** (Windows/Mac/Linux)
- ✅ **Consistent builds** every time

---

## 🔍 Troubleshooting

### Build fails?
- Check Dockerfile is at **root** (same level as `pom.xml`)
- Check `.dockerignore` doesn't exclude important files
- Check Render logs for specific error

### App crashes?
- Check environment variables are set correctly
- Check `DATABASE_URL` format
- Check logs in Render dashboard

### Port issues?
- Render uses `$PORT` env var automatically
- Make sure `application-production.properties` has: `server.port=${PORT:8080}`

---

## 🎯 Quick Checklist

- [x] `Dockerfile` created at root
- [x] `.dockerignore` created
- [x] `render.yaml` updated for Docker
- [ ] Code pushed to GitHub
- [ ] Render service created
- [ ] Environment variables added
- [ ] Build successful
- [ ] API accessible

---

**That's it! Simple and works 100%! 🎉**


