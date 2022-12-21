(ns advent-of-code.day-05
  (:require [clojure.string :as str]))

(def stacks [
  [\p \v \z \w \d \t] 
  [\d \j \f \v \w \s \l] 
  [\h \b \t \v \s \l \m \z] 
  [\j \s \r] 
  [\w \l \m \f \g \b \z \c] 
  [\b \g \r \z \h \v \w \q] 
  [\n \d \b \c \p \j \v] 
  [\q \b \t \p] 
  [\c \r \z \g \h]
])

(defn parse-move "Format: move n from a to b" [line]
  (map read-string (re-seq #"\d+" line)))

(defn move [ordering stacks [n from to]]
  (let [from-idx (dec from) 
        to-idx (dec to)
        from' (nth stacks from-idx)
        to' (nth stacks to-idx) ]
        (-> (assoc stacks from-idx (drop n from'))
            (assoc to-idx (concat (ordering (take n from')) to')))))

(defn solve [ordering input]
  (map first (reduce (partial move ordering) stacks (map parse-move (str/split-lines input)))))

(defn part-1
  "Day 05 Part 1"
  [input]
  (solve reverse input))

(defn part-2
  "Day 05 Part 2"
  [input]
  (solve identity input))
