import axios from "axios";
import React, { useState } from "react";

export const SignUp = () => {
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8082/auth/signup", formData);
            console.log(response.data);
        } catch (error) {
            console.log(error.message);
        }
    };

    return (
        <>
            <div className="container col-xl-10 col-xxl-8 px-4 py-5">
                <div className="row align-items-center g-lg-5 py-5">
                    <div className="col-lg-7 text-center text-lg-start">
                        <h1 className="display-4 fw-bold lh-1 text-body-emphasis mb-3">Join Our Gym</h1>
                        <p className="col-lg-10 fs-4">
                            Whether you're starting out or leveling up, we're here to inspire and guide you. Join us today and become the best version of yourself!
                        </p>
                    </div>
                    <div className="col-md-10 mx-auto col-lg-5">
                        <form className="p-4 p-md-5 border rounded-3 bg-body-tertiary" onSubmit={handleSubmit}>
                            <div className="form-floating mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    id="floatingName"
                                    name="name"
                                    placeholder="John Doe"
                                    value={formData.name}
                                    onChange={handleChange}
                                />
                                <label htmlFor="floatingName">Name</label>
                            </div>
                            <div className="form-floating mb-3">
                                <input
                                    type="email"
                                    className="form-control"
                                    id="floatingEmail"
                                    name="email"
                                    placeholder="name@example.com"
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                                <label htmlFor="floatingEmail">Email address</label>
                            </div>
                            <div className="form-floating mb-3">
                                <input
                                    type="password"
                                    className="form-control"
                                    id="floatingPassword"
                                    name="password"
                                    placeholder="Password"
                                    value={formData.password}
                                    onChange={handleChange}
                                />
                                <label htmlFor="floatingPassword">Password</label>
                            </div>
                            <button className="w-100 btn btn-lg btn-primary" type="submit">
                                Sign up
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
};