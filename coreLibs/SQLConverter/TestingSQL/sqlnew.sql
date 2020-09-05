 SELECT a.calendar_date AS d_date
				,b.calendar_date AS d_date1
			FROM SYS_CALENDAR.CALENDAR a
			LEFT JOIN SYS_CALENDAR.CALENDAR b ON a.calendar_date BETWEEN DATE '${N_DATE}'
					AND DATE '${C_DATE}'
				AND b.calendar_date BETWEEN DATE '${N_DATE}'
					AND DATE '${C_DATE}'
				AND EXTRACT(YEAR FROM b.calendar_date) = EXTRACT(YEAR FROM a.calendar_date)
				AND EXTRACT(MONTH FROM b.calendar_date) = EXTRACT(MONTH FROM a.calendar_date)
				AND b.calendar_date <= a.calendar_date