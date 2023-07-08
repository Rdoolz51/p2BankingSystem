/* eslint-disable @next/next/no-img-element */
// 'use client'
import Link from "next/link"
import Image from "next/image";

import styles from './News.module.css'
import { content } from "./content";

async function getData() {
  const pageSize = 5
  try {
    const res = await fetch(`https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=${process.env.NEWS_API}&pageSize=${pageSize}`);
    if(res.ok) {
      const data = await res.json();
      return data;
    } else {
      return null;
    }
  } catch (e) {
    console.error('Something went wrong fetching news...', e)
    return null;
  }
}


const News = async () => {
  let news = content; //backup in case our API fails to fetch

  // const data = await getData();
  // if(data) {
  //   news = data.articles;
  // }

  return (
    <div className={styles.cardContainer}>
      {news.map((c) => {
        return (
          <div className={styles.card} key={c.publishedAt}>

            <div>
              <Link href={c.url}><img alt="news-image" src={c.urlToImage} className={styles.img}/></Link>
            </div>

            <div className={styles.cardContent}>
              <Link href={c.url}><h2 className={styles.title}>{c?.title}</h2></Link>
              <Link href={c.url}><h3>{c.content?.substring(0, 200)}</h3></Link>
            </div>
          </div>
        )
      })}
    </div>
  )
}

export default News;