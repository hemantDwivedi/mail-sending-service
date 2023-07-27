import React, { useState } from 'react'
import '../css/NewsletterComponent.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const NewsletterComponent = () => {

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [subscribed, setSubscribed] = useState(false)
    const apiUrl = 'http://localhost:8080'
    const navigate = useNavigate()

    const subscribeHanlder = () => {
        axios.post(apiUrl + '/api/subscribe', { name, email })
            .then(navigate('/success'))
            .catch(error => console.error(error))
    }


    return (
        <div>
            <div className='container d-flex align-items-center justify-content-center' style={{ height: '100vh' }}>
                <div className='card'>
                    <div className="card-body p-5">
                        <h1 className='fw-bold fs-4 text-center mb-4'>Subscribe to our Newsletter</h1>
                        <form>
                            <div className='form-group mb-3'>
                                <input
                                    type="text"
                                    value={name}
                                    className='form-control'
                                    placeholder='Your name'
                                    onChange={(e) => setName(e.target.value)}
                                />
                            </div>
                            <div className='form-group mb-2'>
                                <input
                                    type="email"
                                    value={email}
                                    className='form-control'
                                    placeholder='Enter your email'
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </div>
                            <button onClick={subscribeHanlder} className='btn mt-2 text-light fw-bold'>Subscribe</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default NewsletterComponent