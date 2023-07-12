/** @type {import('next').NextConfig} */
const nextConfig = {
    env: {
        API_URL: process.env.API_URL,
        NEXTAUTH_URL: process.env.NEXTAUTH_URL,
    }
}

module.exports = nextConfig