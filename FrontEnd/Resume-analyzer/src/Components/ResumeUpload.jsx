import React, { useState } from 'react';
import axios from 'axios';

const ResumeUpload = () => {
    const [file, setFile] = useState(null);
    const [uploading, setUploading] = useState(false);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const userId = localStorage.getItem('userId') || 3; // Replace with actual logic if needed

        if (!file) {
            alert('Please select a file.');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);
        formData.append('userId', userId);

        setUploading(true);

        try {
            const response = await axios.post('http://localhost:8080/resume/upload', formData);
            if (response.status === 200) {
                alert('File uploaded successfully!');
                console.log('Response:', response.data);
            } else {
                alert('Failed to upload file.');
            }
        } catch (error) {
            console.error('Upload error:', error);
            alert('An error occurred while uploading the file.');
        } finally {
            setUploading(false);
        }
    };

    return (
        <div className="card" style={{ width: '18rem', margin: 'auto', marginTop: '2rem' }}>
            <div className="card-body">
                <h5 className="card-title">Upload Your Resume</h5>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="resumeFile">Choose a file</label>
                        <input
                            type="file"
                            className="form-control-file"
                            id="resumeFile"
                            onChange={handleFileChange}
                            accept=".pdf,.doc,.docx"
                        />
                    </div>
                    <button
                        type="submit"
                        className="btn btn-primary"
                        style={{ marginTop: '1rem' }}
                        disabled={uploading}
                    >
                        {uploading ? 'Uploading...' : 'Submit'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default ResumeUpload;
