import React, { useState } from 'react'
import '../css/NewsletterComponent.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const NewsletterComponent = () => {

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [subscribed, setSubscribed] = useState(false)
    const [error, setError] = useState('')
    const apiUrl = 'http://localhost:8080'
    const navigate = useNavigate()

    const subscribeHanlder = (event) => {
        event.preventDefault()
        if (!name.trim() || !email.trim()) {
            setError('Please enter both your name and email')
            return
        }
        axios.post(apiUrl + '/api/subscribe', { name, email })
            .then(() => {
                setSubscribed(true)
                navigate('/success')
            })
            .catch(error => {
                setError('Error occured while subscribing. Please try again later.')
                console.error(error)
            })
    }


    return (
        <div>
            <div className='container d-flex align-items-center justify-content-center' style={{ height: '100vh' }}>
                <div className='card'>
                    {subscribed ? (<div className='alert alert-success mt-3'>You are subscribed successfully!</div>)
                        : (
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
                                    <button type='submit' onClick={subscribeHanlder} className='btn my-2 text-light fw-bold'>Subscribe</button>
                                    {error && <div className='alert alert-color text-light my-2 p-2 text-center'>{error}</div>}
                                </form>
                            </div>
                        )}
                </div>
            </div>
        </div>
    )
}

export default NewsletterComponent