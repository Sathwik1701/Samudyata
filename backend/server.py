from flask import Flask, request, jsonify, send_from_directory
from flask_cors import CORS
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
import os
from dotenv import load_dotenv

load_dotenv()

app = Flask(__name__)
CORS(app)

@app.route('/api/appointment', methods=['POST'])
def appointment():
    data = request.json
    
    try:
        # Email configuration
        smtp_server = "smtp.gmail.com"
        smtp_port = 587
        sender_email = os.getenv('EMAIL_USER')
        sender_password = os.getenv('EMAIL_PASS')
        
        # Create message
        msg = MIMEMultipart()
        msg['From'] = sender_email
        msg['To'] = sender_email
        msg['Subject'] = "New Appointment Request"
        
        body = f"""
        New Appointment Request:
        
        Name: {data.get('name')}
        Email: {data.get('email')}
        Mobile: {data.get('mobile')}
        Purpose: {data.get('purpose')}
        """
        
        msg.attach(MIMEText(body, 'plain'))
        
        # Send email
        server = smtplib.SMTP(smtp_server, smtp_port)
        server.starttls()
        server.login(sender_email, sender_password)
        server.send_message(msg)
        server.quit()
        
        return jsonify({'success': True, 'message': 'Appointment request sent successfully!'})
    except Exception as e:
        return jsonify({'success': False, 'message': 'Failed to send appointment request'}), 500

@app.route('/api/newsletter', methods=['POST'])
def newsletter():
    data = request.json
    
    try:
        # Email configuration
        smtp_server = "smtp.gmail.com"
        smtp_port = 587
        sender_email = os.getenv('EMAIL_USER')
        sender_password = os.getenv('EMAIL_PASS')
        
        # Create message
        msg = MIMEMultipart()
        msg['From'] = sender_email
        msg['To'] = sender_email
        msg['Subject'] = "New Newsletter Subscription"
        
        body = f"New newsletter subscription: {data.get('email')}"
        msg.attach(MIMEText(body, 'plain'))
        
        # Send email
        server = smtplib.SMTP(smtp_server, smtp_port)
        server.starttls()
        server.login(sender_email, sender_password)
        server.send_message(msg)
        server.quit()
        
        return jsonify({'success': True, 'message': 'Subscribed successfully!'})
    except Exception as e:
        return jsonify({'success': False, 'message': 'Failed to subscribe'}), 500

@app.route('/api/health')
def health():
    return jsonify({'status': 'OK', 'message': 'Server is running'})

@app.route('/')
def index():
    return send_from_directory('../code', 'landing.html')

if __name__ == '__main__':
    app.run(debug=True, port=3000)