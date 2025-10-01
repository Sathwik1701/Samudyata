# Samudyata Infra Solutions Website

## Backend Setup

### Prerequisites
- Node.js (v14 or higher)
- npm or yarn

### Installation

1. Navigate to backend directory:
```bash
cd backend
```

2. Install dependencies:
```bash
npm install
```

3. Setup environment variables:
```bash
cp .env.example .env
```

4. Edit `.env` file with your email credentials:
```
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your-app-password
PORT=3000
```

### Gmail Setup
1. Enable 2-factor authentication on your Gmail account
2. Generate App Password: https://myaccount.google.com/apppasswords
3. Use the App Password (not your regular password) in EMAIL_PASS

### Running the Server

Development mode:
```bash
npm run dev
```

Production mode:
```bash
npm start
```

The server will run on http://localhost:3000

## Features

### Backend API Endpoints
- `POST /api/appointment` - Handle appointment form submissions
- `POST /api/newsletter` - Handle newsletter subscriptions
- `GET /api/health` - Health check endpoint
- `GET /` - Serve the landing page

### Frontend Features
- Responsive design with Tailwind CSS
- Interactive appointment booking modal
- Newsletter subscription
- Smooth scrolling navigation
- Modern animations and effects

## File Structure
```
samudyata/
├── backend/
│   ├── server.js
│   ├── package.json
│   └── .env.example
├── code/
│   ├── landing.html
│   ├── portfolio.html
│   └── assets/
└── README.md
```

## Deployment
The backend can be deployed to platforms like:
- Heroku
- Vercel
- Railway
- DigitalOcean

Make sure to set environment variables in your deployment platform.